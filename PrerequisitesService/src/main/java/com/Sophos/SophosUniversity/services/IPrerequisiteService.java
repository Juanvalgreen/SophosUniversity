package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.Prerequisite;

import java.util.List;

public interface IPrerequisiteService {
    List<Prerequisite> getAllPrerequisites() throws Exception;

    List<Prerequisite> getPrerequistesByCourseId(Long id) throws Exception;

    String updatePrerequiste(Prerequisite prerequisite) throws Exception;

    String addNewPrerequisite(Prerequisite prerequisite) throws Exception;

    String deletePrerequisite(Long id) throws Exception;
}
