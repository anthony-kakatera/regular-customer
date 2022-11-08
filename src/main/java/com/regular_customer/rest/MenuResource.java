package com.regular_customer.rest;

import com.regular_customer.model.MenuDTO;
import com.regular_customer.service.MenuService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuResource {

    private final MenuService menuService;

    public MenuResource(final MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return ResponseEntity.ok(menuService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable final Integer id) {
        return ResponseEntity.ok(menuService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createMenu(@RequestBody @Valid final MenuDTO menuDTO) {
        return new ResponseEntity<>(menuService.create(menuDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMenu(@PathVariable final Integer id,
            @RequestBody @Valid final MenuDTO menuDTO) {
        menuService.update(id, menuDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteMenu(@PathVariable final Integer id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
