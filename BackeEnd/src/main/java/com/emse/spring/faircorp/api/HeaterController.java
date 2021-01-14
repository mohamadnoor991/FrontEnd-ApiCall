package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/Heaters")
@Transactional
public class HeaterController {



    @Autowired
    HeaterDao heaterDaoAu;
        private final RoomDao roomDao;
        private final WindowDao windowDao;
        private final HeaterDao heaterDao;
        private final BuildingDao buildingDao;

        public HeaterController(RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao, BuildingDao buildingDao) { // (4)
            this.roomDao = roomDao;
            this.windowDao = windowDao;
            this.heaterDao = heaterDao;
            this.buildingDao = buildingDao;
        }
        @GetMapping
        public List<HeaterDto> findAll() {
            return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());  // (6)
        }
        @GetMapping(path = "/{heater_id}")
        public HeaterDto findById(@PathVariable Long heater_id) {
            return heaterDao.findById(heater_id).map(HeaterDto::new).orElse(null); // (7)
        }
        @PostMapping
        public HeaterDto create(@RequestBody HeaterDto dto) {
            Room room = roomDao.getOne(dto.getRoomId());
            return new HeaterDto(heaterDao.save(new Heater(dto.getName(),room, dto.getHeaterStatus(), dto.getPower())));


        }

        @DeleteMapping(path = "/{heater_id}")
        public void delete(@PathVariable Long heater_id) {
            heaterDao.deleteById(heater_id);
        }
}

