package com.regular_customer.service;

import com.regular_customer.domain.Favourite;
import com.regular_customer.domain.Menu;
import com.regular_customer.domain.User;
import com.regular_customer.model.FavouriteDTO;
import com.regular_customer.repos.FavouriteRepository;
import com.regular_customer.repos.MenuRepository;
import com.regular_customer.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public FavouriteService(final FavouriteRepository favouriteRepository,
            final MenuRepository menuRepository, final UserRepository userRepository) {
        this.favouriteRepository = favouriteRepository;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    public List<FavouriteDTO> findAll() {
        return favouriteRepository.findAll(Sort.by("id"))
                .stream()
                .map(favourite -> mapToDTO(favourite, new FavouriteDTO()))
                .collect(Collectors.toList());
    }

    public FavouriteDTO get(final Integer id) {
        return favouriteRepository.findById(id)
                .map(favourite -> mapToDTO(favourite, new FavouriteDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final FavouriteDTO favouriteDTO) {
        final Favourite favourite = new Favourite();
        mapToEntity(favouriteDTO, favourite);
        return favouriteRepository.save(favourite).getId();
    }

    public void update(final Integer id, final FavouriteDTO favouriteDTO) {
        final Favourite favourite = favouriteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(favouriteDTO, favourite);
        favouriteRepository.save(favourite);
    }

    public void delete(final Integer id) {
        favouriteRepository.deleteById(id);
    }

    private FavouriteDTO mapToDTO(final Favourite favourite, final FavouriteDTO favouriteDTO) {
        favouriteDTO.setId(favourite.getId());
        favouriteDTO.setMenu(favourite.getMenu() == null ? null : favourite.getMenu().getId());
        favouriteDTO.setUser(favourite.getUser() == null ? null : favourite.getUser().getId());
        return favouriteDTO;
    }

    private Favourite mapToEntity(final FavouriteDTO favouriteDTO, final Favourite favourite) {
        final Menu menu = favouriteDTO.getMenu() == null ? null : menuRepository.findById(favouriteDTO.getMenu())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "menu not found"));
        favourite.setMenu(menu);
        final User user = favouriteDTO.getUser() == null ? null : userRepository.findById(favouriteDTO.getUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        favourite.setUser(user);
        return favourite;
    }

}
