package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.dtos.prerequisiteDTO;
import com.Sophos.SophosUniversity.entities.Prerequisite;

import java.util.List;

public interface IPrerequisiteService {
    List<prerequisiteDTO> getAllPrerequisites() throws Exception;

    List<prerequisiteDTO> getPrerequistesByCourseId(Long id) throws Exception;

    String updatePrerequiste(Prerequisite prerequisite) throws Exception;

    String addNewPrerequisite(Prerequisite prerequisite) throws Exception;

    String deletePrerequisite(Long id) throws Exception;
}
