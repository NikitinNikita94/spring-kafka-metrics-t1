package t1.springmetricsconsumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t1.springmetricsconsumer.exception.MetricNotFoundException;
import t1.springmetricsconsumer.persist.ReadMetricsDto;
import t1.springmetricsconsumer.persist.mapper.MetricMapper;
import t1.springmetricsconsumer.persist.repository.MetricRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MetricService {

    private final MetricRepository repository;

    /**
     * Метод возвращает метрику по айди.
     *
     * @param id - айди метрики.
     * @return - дто.
     */
    public ReadMetricsDto getMetricsById(final UUID id) {
        return repository.findById(id)
                .map(MetricMapper.INSTANCE::toReadMetricsDto)
                .orElseThrow(() -> new MetricNotFoundException("Could not find metric with id: " + id));
    }

    /**
     * Метод возвращает все метрики из бд.
     *
     * @param pageable - постраничный вывод.
     * @return - коллекцию метрик.
     */
    public List<ReadMetricsDto> getAllMetrics(final Pageable pageable) {
        return repository.findAll(pageable)
                .stream()
                .map(MetricMapper.INSTANCE::toReadMetricsDto)
                .toList();
    }

    /**
     * Метод по сохранению метрик в бд.
     *
     * @param readMetricsDto - дто с метрикой.
     */
    public void saveMetrics(final ReadMetricsDto readMetricsDto) {
        Optional.of(readMetricsDto)
                .map(MetricMapper.INSTANCE::toMetric)
                .map(repository::saveAndFlush)
                .orElseThrow();
    }
}
