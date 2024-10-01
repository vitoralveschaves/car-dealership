package br.com.api.dealership.services.impl;

import br.com.api.dealership.dtos.MakeRequestDto;
import br.com.api.dealership.dtos.MakeResponseDto;
import br.com.api.dealership.entities.Make;
import br.com.api.dealership.mappers.MakeMapper;
import br.com.api.dealership.repositories.MakeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MakeServiceImplTest {

    @Mock
    private MakeRepository makeRepository;
    @Mock
    private MakeMapper makeMapper;

    @InjectMocks
    private MakeServiceImpl makeService;

    @Captor
    private ArgumentCaptor<Make> makeArgumentCaptor;
    @Captor
    private ArgumentCaptor<MakeRequestDto> requestArgumentCaptor;

    @Nested
    class Register {
        @Test
        @DisplayName("Should register a make with success")
        @MockitoSettings(strictness = Strictness.LENIENT)
        void registerAMakeWithSuccess() {

            Make make = new Make(1L, "Toyota", "123");
            MakeRequestDto request = new MakeRequestDto("Toyota", "123");
            MakeResponseDto response = new MakeResponseDto(1L, "Toyota", "123");

            doReturn(make).when(makeMapper).dtoToEntity(requestArgumentCaptor.capture());
            doReturn(make).when(makeRepository).save(makeArgumentCaptor.capture());
            doReturn(response).when(makeMapper).entityToDto(makeArgumentCaptor.capture());

            var res = makeService.register(request);

            assertNotNull(res);
            assertEquals(res.id(), makeArgumentCaptor.getAllValues().getFirst().getId());
            assertEquals(res.name(), makeArgumentCaptor.getAllValues().getFirst().getName());
            assertEquals(res.document(), makeArgumentCaptor.getAllValues().getFirst().getDocument());

            verify(makeMapper, times(1)).dtoToEntity(requestArgumentCaptor.getValue());
            verify(makeRepository, times(1)).save(makeArgumentCaptor.getAllValues().get(0));
            verify(makeMapper, times(1)).entityToDto(makeArgumentCaptor.getAllValues().get(1));
        }

        @Nested
        class GetAll {
            @Test
            @DisplayName("Should get all makes with success")
            @MockitoSettings(strictness = Strictness.LENIENT)
            void getAllMakesWithSuccess() {
                Make make = new Make(1L, "Toyota", "123");
                doReturn(List.of(make)).when(makeRepository).findAll();

                MakeResponseDto makeDto = new MakeResponseDto(1L, "Toyota", "123");

                doReturn(makeDto).when(makeMapper).entityToDto(any(Make.class));

                List<MakeResponseDto> all = makeService.getAll();
                assertNotNull(all);
                assertEquals(List.of(makeDto), all);
            }
        }

    }
}