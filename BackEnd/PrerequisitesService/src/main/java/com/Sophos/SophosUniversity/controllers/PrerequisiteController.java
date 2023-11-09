package com.Sophos.SophosUniversity.controllers;

import com.Sophos.SophosUniversity.dtos.prerequisiteDTO;
import com.Sophos.SophosUniversity.entities.Prerequisite;
import com.Sophos.SophosUniversity.services.IPrerequisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PrerequisiteController {


    @Autowired
    private IPrerequisiteService service;

    @GetMapping("/api/v1/prerequisites")
    public List<prerequisiteDTO> getAllPrerequisites() throws Exception {
        return service.getAllPrerequisites();
    }

    @GetMapping("/api/v1/prerequisites/{id}/courses")
    public List<prerequisiteDTO> getPrerequisitesByCourseId(@PathVariable Long id) throws Exception {
        return service.getPrerequistesByCourseId(id);
    }

    @PostMapping("/api/v1/prerequisites")
    public String addNewPrerequisites(@RequestBody Prerequisite prerequisite) throws Exception {
        return service.addNewPrerequisite(prerequisite);
    }

    @PutMapping("/api/v1/prerequisites")
    public String updatePrerequisites(@RequestBody Prerequisite prerequisite) throws Exception {
        return service.updatePrerequiste(prerequisite);
    }

    @DeleteMapping("/api/v1/prerequisites/{id}")
    public String deletePrerequisite(@PathVariable Long id) throws Exception{
        return service.deletePrerequisite(id);
    }

}
