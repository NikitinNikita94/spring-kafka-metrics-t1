package t1.springmetricsconsumer.persist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import t1.springmetricsconsumer.persist.ReadMetricsDto;
import t1.springmetricsconsumer.persist.entity.Metric;


@Mapper(componentModel = "spring")
public interface MetricMapper {

    MetricMapper INSTANCE = Mappers.getMapper(MetricMapper.class);

    ReadMetricsDto toReadMetricsDto(Metric metric);

    Metric toMetric(ReadMetricsDto readMetricsDto);
}
