package org.med.youhospital.serverside.service;

import org.med.youhospital.serverside.model.request.RecipeReq;
import org.med.youhospital.serverside.model.response.RecipeRes;

import java.util.List;
import java.util.UUID;

public interface RecipeService extends BaseService<RecipeReq, UUID, RecipeRes> {
}
