@Service
@RequiredArgsConstructor
public class ConsumptionLogService {

    private final ConsumptionLogRepository repository;
    private final StockRecordRepository stockRepo;

    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {

        StockRecord stock = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        if (log.getConsumedQuantity() <= 0)
            throw new IllegalArgumentException("Invalid consumed quantity");

        if (log.getConsumedDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("consumedDate cannot be future");

        log.setStockRecord(stock);
        return repository.save(log);
    }

    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return repository.findByStockRecordId(stockRecordId);
    }

    public ConsumptionLog getLog(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }
}
