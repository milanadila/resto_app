package com.milan.resto.service;

import com.milan.resto.dto.TableRequestDto;
import com.milan.resto.dto.TableResponseDto;
import com.milan.resto.entity.TableResto;
import com.milan.resto.exception.IdNotFoundException;
import com.milan.resto.repository.TableRestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableRestoService {

    @Autowired
    TableRestoRepository tableRestoRepository;

    public List<TableResto> findAll() {
        return tableRestoRepository.findAll();
    }

    public TableResto findById(Integer id) {
        return tableRestoRepository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    public TableResponseDto chooseTable(TableRequestDto tableRequestDto) {
        TableResto tableResto = new TableResto();
        tableResto.setTableNumber(tableRequestDto.getTableNumber());
        tableResto.setTableCapacity(tableRequestDto.getTableCapacity());
        tableResto.setTableAvailability(true);
        tableRestoRepository.save(tableResto);

        TableResponseDto tableResponseDto = new TableResponseDto();
        tableResponseDto.setTableId(tableResto.getTableId());
        tableResponseDto.setTableNumber(tableResto.getTableNumber());
        tableResponseDto.setTableCapacity(tableResto.getTableCapacity());
        tableResponseDto.setTableAvailability(true);

        return tableResponseDto;
    }
}
