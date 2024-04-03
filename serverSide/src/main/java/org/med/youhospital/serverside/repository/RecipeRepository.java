package org.med.youhospital.serverside.repository;

import org.med.youhospital.serverside.model.entity.Examination;
import org.med.youhospital.serverside.model.entity.Recipe;
import org.med.youhospital.serverside.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
}