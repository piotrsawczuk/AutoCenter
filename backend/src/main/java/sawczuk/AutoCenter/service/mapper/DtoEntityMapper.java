package sawczuk.AutoCenter.controller.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class DtoEntityMapper {

    private static ModelMapper modelMapper;
    private static ModelMapper modelMapperAcceptNulls;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapperAcceptNulls = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private DtoEntityMapper() {
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <S, D> void map(final S source, D destination) {
        modelMapper.map(source, destination);
    }

    public static <S, D> void mapWithNulls(final S source, D destination) {
        modelMapperAcceptNulls.map(source, destination);
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList
                .stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public static <D, T> Page<D> mapAll(final Page<T> entityPage, Class<D> outCLass) {
        return entityPage
                .map(entity -> map(entity, outCLass));
    }

}