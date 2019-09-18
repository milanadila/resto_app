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

    public TableResponseDto chooseTable(TableRequestDto tableRequestDto) throws Exception{
        TableResto tableResto = tableRestoRepository.findByTableNumber(tableRequestDto.getTableNumber());
        Boolean status = tableResto.getTableAvailability();
        if (status == false) {
            throw new Exception("Table is not available");
        }
        tableResto.setTableAvailability(false);
        tableRestoRepository.save(tableResto);

        TableResponseDto tableResponseDto = new TableResponseDto();
        tableResponseDto.setTableId(tableResto.getTableId());
        tableResponseDto.setTableNumber(tableResto.getTableNumber());
        tableResponseDto.setTableCapacity(tableResto.getTableCapacity());
        tableResponseDto.setTableAvailability(false);

        return tableResponseDto;
    }

    public TableResto findByTableNumber(Integer tableNumber) {
        return tableRestoRepository.findByTableNumber(tableNumber);
    }

    public void save(TableResto tableResto) {
        tableRestoRepository.save(tableResto);
    }
}
