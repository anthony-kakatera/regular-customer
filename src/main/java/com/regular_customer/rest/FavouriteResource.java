package com.regular_customer.rest;

import com.regular_customer.model.FavouriteDTO;
import com.regular_customer.service.FavouriteService;
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
@RequestMapping(value = "/api/favourites", produces = MediaType.APPLICATION_JSON_VALUE)
public class FavouriteResource {

    private final FavouriteService favouriteService;

    public FavouriteResource(final FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping
    public ResponseEntity<List<FavouriteDTO>> getAllFavourites() {
        return ResponseEntity.ok(favouriteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavouriteDTO> getFavourite(@PathVariable final Integer id) {
        return ResponseEntity.ok(favouriteService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createFavourite(
            @RequestBody @Valid final FavouriteDTO favouriteDTO) {
        return new ResponseEntity<>(favouriteService.create(favouriteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFavourite(@PathVariable final Integer id,
            @RequestBody @Valid final FavouriteDTO favouriteDTO) {
        favouriteService.update(id, favouriteDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFavourite(@PathVariable final Integer id) {
        favouriteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
