package com.technova.campussphereapi.mapper;
import com.technova.campussphereapi.dto.RatingCreateUpdateDTO;
import com.technova.campussphereapi.dto.RatingDetailsDTO;
import com.technova.campussphereapi.model.entity.Rating;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    private final ModelMapper modelMapper;

    public RatingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public RatingDetailsDTO toDetailsDTO(Rating rating) {
        RatingDetailsDTO ratingDetailsDTO = modelMapper.map(rating, RatingDetailsDTO.class);

        ratingDetailsDTO.setEventName(rating.getEvent().getName());
        ratingDetailsDTO.setStudentName(rating.getUser().getStudent().getFirstName() + " " + rating.getUser().getStudent().getLastName());

        return ratingDetailsDTO;
    }

    public Rating toEntity(RatingCreateUpdateDTO ratingCreateUpdateDTO) {
        return modelMapper.map(ratingCreateUpdateDTO, Rating.class);
    }

    public RatingCreateUpdateDTO toCreateUpdateDTO(Rating rating) {
        return modelMapper.map(rating, RatingCreateUpdateDTO.class);
    }
}