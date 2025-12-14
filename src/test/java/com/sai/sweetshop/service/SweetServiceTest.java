package com.sai.sweetshop.service;

import com.sai.sweetshop.model.Sweet;
import com.sai.sweetshop.repository.SweetRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SweetServiceTest {

    @Mock
    private SweetRepository sweetRepository;

    @InjectMocks
    private SweetService sweetService;

    private Sweet sweet;

    @BeforeEach
    void setUp() {
        sweet = new Sweet();
        sweet.setId(1L);
        sweet.setName("Kaju Katli");
        sweet.setCategory("Indian");
        sweet.setPrice(500);
        sweet.setQuantity(10);
    }

    // ========================
    // RESTOCK
    // ========================
    @Test
    void restock_shouldIncreaseQuantity() {
        when(sweetRepository.findById(1L)).thenReturn(Optional.of(sweet));
        when(sweetRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Sweet result = sweetService.restock(1L, 5);

        assertEquals(15, result.getQuantity());
    }

    // ========================
    // DELETE
    // ========================
    @Test
    void delete_shouldThrowException_ifSweetNotFound() {
        when(sweetRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> sweetService.delete(1L));
    }

    @Test
    void delete_shouldDeleteSweet() {
        when(sweetRepository.existsById(1L)).thenReturn(true);

        sweetService.delete(1L);

        verify(sweetRepository).deleteById(1L);
    }


    // ========================
    // SEARCH
    // ========================
    @Test
    void search_shouldReturnAll_whenNoFilter() {
        when(sweetRepository.findAll()).thenReturn(List.of(sweet));

        List<Sweet> result = sweetService.search(
                null, null, null, null, null, null, null
        );

        assertEquals(1, result.size());
    }

    @Test
    void search_shouldFilterByName() {
        when(sweetRepository.findAll()).thenReturn(List.of(sweet));

        List<Sweet> result = sweetService.search(
                null, "kaju", null, null, null, null, null
        );

        assertEquals(1, result.size());
    }
}
