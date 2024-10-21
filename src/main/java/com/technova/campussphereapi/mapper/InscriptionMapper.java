package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.model.entity.Inscription;
import com.technova.campussphereapi.model.entity.User;
import com.technova.campussphereapi.model.enums.InscriptionStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.modelmapper.convention.MatchingStrategies;

@Component
public class InscriptionMapper {

    private final ModelMapper modelMapper;

    public InscriptionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public InscriptionDetailsDTO toDetailsDTO(Inscription inscription) {
        InscriptionDetailsDTO inscriptionDetailsDTO = modelMapper.map(inscription, InscriptionDetailsDTO.class);

        inscriptionDetailsDTO.setEventName(inscription.getEvent().getName());
        inscriptionDetailsDTO.setStudentName(inscription.getUser().getStudent().getFirstName() + " " + inscription.getUser().getStudent().getLastName());
        inscriptionDetailsDTO.setInscriptionStatus(InscriptionStatus.PENDING);

        return inscriptionDetailsDTO;
    }

    public Inscription toEntity(InscriptionCreateUpdateDTO inscriptionCreateUpdateDTO) {
        Inscription inscription = modelMapper.map(inscriptionCreateUpdateDTO, Inscription.class);
        User user = new User();
        user.setId(inscriptionCreateUpdateDTO.getUserId());
        inscription.setUser(user);

        return inscription;
    }

    public InscriptionCreateUpdateDTO toCreateUpdateDTO(Inscription inscription) {
        return modelMapper.map(inscription, InscriptionCreateUpdateDTO.class);
    }

}
