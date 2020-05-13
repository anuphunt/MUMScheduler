package edu.mum.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Block;
import edu.mum.domain.Course;
import edu.mum.domain.Faculty;
import edu.mum.repository.CourseRepository;
import edu.mum.repository.FacultyRepository;

@Service
public class FacultyHelper {

	@Autowired
	private CourseRepository courseDao;
	@Autowired
	private FacultyRepository facultyDao;
	private List<Course> courses;
	private List<Faculty> faculties;
	private Course FPP = new Course();
	private Course MPP = new Course();

	public void assignCourseAndFaculty(Block block) {
		courses = (List<Course>) courseDao.findAll();
		faculties = (List<Faculty>) facultyDao.findAll();
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCourseName().equalsIgnoreCase("FPP"))
				this.FPP = courses.get(i);
			if (courses.get(i).getCourseName().equalsIgnoreCase("MPP"))
				this.MPP = courses.get(i);
		}

		Map<Faculty, Course> facultyCourseMap = new HashMap<>();

		if (block.getBlockOrder() > 1) {
			List<Course> choosen = getCourse(block.getBlockOrder(), block.getSections().size(),
					(int) (Math.floor(block.getEntry().getNumOfFpp()) / 25));
			if (block.getBlockOrder() == 2) {
				for (int i = 0; i < Math.floor(block.getEntry().getNumOfFpp()) / 25; i++) {
					choosen.add(this.MPP);
				}
			}
			facultyCourseMap = getFaculty(choosen, block.getBlockOrder(), block.getSections().size());
		} else if (block.getBlockOrder() == 1) {
			facultyCourseMap = getFirstBlockFaculty((int) Math.floor(block.getEntry().getNumOfFpp() / 25),
					(int) Math.floor(block.getEntry().getNumOfMpp() / 25));
		}

		int cnt = 0;
		for (Faculty key : facultyCourseMap.keySet()) {
			block.getSections().get(cnt).setCourse(facultyCourseMap.get(key));
			block.getSections().get(cnt).setFaculty(key);
			cnt++;
			if (block.getSections().size() == cnt)
				break;

		}
		for (Faculty key : facultyCourseMap.keySet()) {
			for (int i = 0; i < block.getSections().size(); i++) {
				if (block.getSections().get(i).getCourse() == null) {
					block.getSections().get(i).setCourse(facultyCourseMap.get(key));
					block.getSections().get(i).setFaculty(new Faculty("UNSTAFFED"));
					break;
				}
			}
		}
	}

	private List<Course> getCourse(long blkId, int noSection, int numOfFpp) {
		List<Course> selected = new ArrayList<>();
		if (blkId == 2) {
			selected.addAll(firstElective(noSection - numOfFpp));
			for (Course c : selected) {
				System.out.println("=======> mpp course check" + c.getCourseName());
			}

		} else if (blkId == 3) {
			selected.addAll(firstElective((int) Math.round(noSection / 2)));
			selected.addAll(get500Courses(noSection - (int) Math.round(noSection / 2)));
		} else if (blkId > 3) {
			selected.addAll(get500Courses((int) Math.round(noSection / 1.5)));
			selected.addAll(get400Courses(noSection - (int) Math.round(noSection / 1.5)));
		}

		return selected;
	}

	private List<Course> firstElective(int noSection) {
		List<Course> selected = new ArrayList<>();
		courses.stream().filter(c -> c.getIsPreReq()).filter(c -> (c.getCourseCode() > 400 && c.getCourseCode() < 500))
				.filter(c -> {
					if (c.getCourseName().equalsIgnoreCase("MPP"))
						return false;
					else
						return true;
				}).forEach(selected::add);
		if (selected.size() > noSection) {
			for (int i = 0; i < (selected.size() - noSection); i++) {
				selected.remove(i);
			}
		} else {
			for (int i = 0, j = 0; i < courses.size() && j < (noSection - selected.size()); i++) {
				if (courses.get(i).getCourseCode() > 400 && courses.get(i).getCourseCode() < 500
						&& (!(courses.get(i).getCourseName().equalsIgnoreCase("MPP")))
						&& !selected.contains(courses.get(i))) {
					selected.add(courses.get(i));
					j++;
				}
			}
		}

		return selected;
	}

	private HashSet<Course> get500Courses(int number) {
		List<Course> course500 = courses.stream().filter(c -> c.getCourseCode() > 500).collect(Collectors.toList());
		HashSet<Course> choosen = new HashSet<>();
		Random rand = new Random();
		// int j = 0;
		for (; choosen.size() < number;) {
			choosen.add(course500.get(rand.nextInt(course500.size())));
			// choosen.add(course500.get(j));
			// j++;
		}
		return choosen;
	}

	private HashSet<Course> get400Courses(int number) {
		List<Course> course400 = courses.stream().filter(c -> c.getCourseCode() < 500 && c.getCourseCode() > 400)
				.filter(c -> !c.getCourseName().equalsIgnoreCase("MPP")).collect(Collectors.toList());
		HashSet<Course> choosen = new HashSet<>();
		Random rand = new Random();
		// int i = 0;
		for (; choosen.size() < number;) {
			choosen.add(course400.get(rand.nextInt(course400.size())));
			// choosen.add(course400.get(i));
			// i++;
		}
		return choosen;
	}

	private Map<Faculty, Course> getFaculty(List<Course> selected, int blkId, int noFpp) {
		Map<Faculty, Course> map = new HashMap<>();
		selected.forEach(c -> {
			for (int j = 0; j < faculties.size(); j++) {
				if (faculties.get(j).getCourse().contains(c) /* && faculties.get(j).aet check availability also */) {
					if (!map.containsKey(faculties.get(j)) && !map.containsValue(c)
							|| !map.containsKey(faculties.get(j)) && c.getCourseName().equalsIgnoreCase("MPP"))
						map.put(faculties.get(j), c);

				}
			}

		});
		int k = map.size();
		Map<Faculty, Course> mapR = new HashMap<>();
		for (Faculty key : map.keySet()) {
			if (map.get(key).getCourseName().equalsIgnoreCase("MPP") && k > noFpp) {
				k--;
				mapR.put(key, map.get(key));
				System.out.println("=====>Mpp checked" + k);
			}
		}
		mapR.keySet().forEach(c -> map.remove(c, mapR.get(c)));

		return map;
	}

	private Map<Faculty, Course> getFirstBlockFaculty(int fppNo, int mppNo) {
		System.out.println("====== facultyHelper " + this.MPP.getCourseName());
		Map<Faculty, Course> map = new HashMap<>();

		List<Faculty> mppFaculty = faculties.stream().filter(f -> {
			for (int i = 0; i < f.getCourse().size(); i++) {
				if (f.getCourse().get(i).getCourseName().equalsIgnoreCase("MPP")) {
					return true;
				}
			}
			return false;
		}).collect(Collectors.toList());
		List<Faculty> fppFaculty = faculties.stream().filter(f -> {
			for (int i = 0; i < f.getCourse().size(); i++) {
				if (f.getCourse().get(i).getCourseName().equalsIgnoreCase("FPP")) {
					return true;
				}
			}
			return false;
		}).collect(Collectors.toList());

		Random rand = new Random();
		// System.out.println("====== facultyHelper1 "+this.MPP.getCourseName());
		for (int i = 0; i < mppNo; i++) {
			if (map.size() < mppNo) {
				map.put(mppFaculty.get(rand.nextInt(mppFaculty.size())), this.MPP);
				System.out.println("====== facultyHelper1 " + this.MPP.getCourseName());
			}

		}
		for (Faculty c : map.keySet()) {
			System.out.println("==== mapp  " + c.getUserProfile().getFirstName() + " " + map.get(c).getCourseName());
		}
		for (int i = 0; i < fppNo; i++) {
			Faculty f = fppFaculty.get(rand.nextInt(fppFaculty.size()));
			if (!map.containsKey(f) && map.size()<(mppNo + fppNo)) {
				map.put((f), this.FPP);
				// System.out.println("====== facultyHelper2 " + this.FPP.getCourseName());
				
			}
		}
		for (Faculty c : map.keySet()) {
			System.out.println("====  fpp " + c.getUserProfile().getFirstName() + " " + map.get(c).getCourseName());
		}
		return map;
	}

}