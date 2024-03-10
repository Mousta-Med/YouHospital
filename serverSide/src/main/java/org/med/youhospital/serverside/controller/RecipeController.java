package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.RecipeReq;
import org.med.youhospital.serverside.model.response.RecipeRes;
import org.med.youhospital.serverside.service.RecipeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController extends BaseController<RecipeReq, UUID, RecipeRes, RecipeService> {
    protected RecipeController(RecipeService service) {
        super(service);
    }
}
