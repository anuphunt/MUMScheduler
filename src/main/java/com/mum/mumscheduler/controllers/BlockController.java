package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.services.IBlockService;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "block")
public class BlockController {

    @Autowired
    public IBlockService blockService;

    @GetMapping("/addblock")
    public String getAddBlockForm(Model model) {

        model.addAttribute("block", new Block());
        return "block/addblockform";
    }

    @RequestMapping(value = "/addblock", method = RequestMethod.POST)
    public String addBlock(@ModelAttribute("block") Block block) {

        blockService.add(block);
        return "redirect:/block/all";
    }

    @GetMapping("/all")
    public String showAllBlocks(Model model) {
        model.addAttribute("blocks", blockService.getAllBlock());
        return "block/showallblock";
    }

    @GetMapping("/updateBlock")
    public String showUpdateForm(@RequestParam("id") String id,
                                 Model model) {
        Block block = blockService.getBlockById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Block Id:" + id));

        model.addAttribute("block", block);

        return "block/addblockform";
    }

    @GetMapping("/delete")
    public String deleteBlock(@RequestParam("id") String id) {
        blockService.delete(id);
        return "redirect:/block/all";
    }

}
