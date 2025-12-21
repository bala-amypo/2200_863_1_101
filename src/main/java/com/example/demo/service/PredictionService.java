@Service
@RequiredArgsConstructor
public class PredictionService {

    private final PredictionRuleRepository ruleRepo;
    private final StockRecordRepository stockRepo;
    private final ConsumptionLogRepository logRepo;

    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0)
            throw new IllegalArgumentException("Invalid average window");

        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepo.save(rule);
    }

    public List<PredictionRule> getAllRules() {
        return ruleRepo.findAll();
    }

    public LocalDate predictRestockDate(Long stockRecordId) {

        StockRecord stock = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        List<ConsumptionLog> logs = logRepo.findByStockRecordId(stockRecordId);

        if (logs.isEmpty()) return LocalDate.now();

        double avg = logs.stream()
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .average()
                .orElse(1);

        int daysLeft = (int) ((stock.getCurrentQuantity() - stock.getReorderThreshold()) / avg);
        return LocalDate.now().plusDays(Math.max(daysLeft, 0));
    }
}
