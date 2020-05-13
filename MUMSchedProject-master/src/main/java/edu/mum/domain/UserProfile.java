package edu.mum.domain;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;




@Entity
//@EqualPasswords(message = "Password and Conform password is not match!")
public class UserProfile {
 
	 
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 @Size(min=4 ,max=50)
	 private String firstName;
	 @Size(min=4 ,max=50)
	 private String lastName;
	 @Size(min=4 ,max=50 )
	// @Unique(service = UserProfileService.class, fieldName = "userName", message = "{field.unique.violation}")
	 private String userName;
	 private String password;
	 @Transient
	 //@NotNull
	 private String confirmpassword;
	 @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	 @Fetch(value=FetchMode.SUBSELECT)
	 private List<Role> roles=new ArrayList<Role>() ;
	 @Email
	 private String email;
	 private String userStatus;
	 private int isActive;
	 
	 public UserProfile() {
			
		}
	 public UserProfile(String staff) {
			super();
			this.firstName=staff;
		}
	 
	 public UserProfile(UserProfile user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.roles=user.getRoles();
		this.email = user.getEmail();
		this.userStatus = user.getUserStatus();
		this.birthdate = user.getBirthdate();
		this.id=user.getId();
	}

	public UserProfile(Long id, @Size(min = 4, max = 50) String firstName, @Size(min = 4, max = 50) String lastName, @Size(min = 4, max = 50) String userName, String password, String confirmpassword, List<Role> roles, @Email String email, String userStatus, int isActive, Date birthdate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.roles = roles;
		this.email = email;
		this.userStatus = userStatus;
		this.isActive = isActive;
		this.birthdate = birthdate;
	}

	public String getUserStatus() {
		return userStatus;
	 }

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	/*@NotNull
	 @Past
	 @DateTimeFormat(pattern = "dd/MM/yyyy")*/
     private Date birthdate;
	 
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	 public Long getId() {
		return id;
	 }
	 public void setId(Long id) {
		this.id = id;
	 }
	 public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((confirmpassword == null) ? 0 : confirmpassword.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + isActive;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userStatus == null) ? 0 : userStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (confirmpassword == null) {
			if (other.confirmpassword != null)
				return false;
		} else if (!confirmpassword.equals(other.confirmpassword))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive != other.isActive)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userStatus == null) {
			if (other.userStatus != null)
				return false;
		} else if (!userStatus.equals(other.userStatus))
			return false;
		return true;
	}
	
	 
}
