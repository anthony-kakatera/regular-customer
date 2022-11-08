package com.regular_customer.service;

import com.regular_customer.domain.Menu;
import com.regular_customer.model.MenuDTO;
import com.regular_customer.repos.MenuRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(final MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<MenuDTO> findAll() {
        return menuRepository.findAll(Sort.by("id"))
                .stream()
                .map(menu -> mapToDTO(menu, new MenuDTO()))
                .collect(Collectors.toList());
    }

    public MenuDTO get(final Integer id) {
        return menuRepository.findById(id)
                .map(menu -> mapToDTO(menu, new MenuDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final MenuDTO menuDTO) {
        final Menu menu = new Menu();
        mapToEntity(menuDTO, menu);
        return menuRepository.save(menu).getId();
    }

    public void update(final Integer id, final MenuDTO menuDTO) {
        final Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(menuDTO, menu);
        menuRepository.save(menu);
    }

    public void delete(final Integer id) {
        menuRepository.deleteById(id);
    }

    private MenuDTO mapToDTO(final Menu menu, final MenuDTO menuDTO) {
        menuDTO.setId(menu.getId());
        menuDTO.setFood(menu.getFood());
        menuDTO.setPrice(menu.getPrice());
        return menuDTO;
    }

    private Menu mapToEntity(final MenuDTO menuDTO, final Menu menu) {
        menu.setFood(menuDTO.getFood());
        menu.setPrice(menuDTO.getPrice());
        return menu;
    }

}
