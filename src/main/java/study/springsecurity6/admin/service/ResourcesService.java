package study.springsecurity6.admin.service;

import study.springsecurity6.entity.Resources;

import java.util.List;

public interface ResourcesService {
    Resources getResources(long id);
    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(long id);
}
