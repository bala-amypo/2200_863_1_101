@Service
@RequiredArgsConstructor
public class StockRecordService {

    private final StockRecordRepository repository;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        repository.findByProductIdAndWarehouseId(productId, warehouseId)
                .ifPresent(r -> { throw new IllegalArgumentException("StockRecord already exists"); });

        if (record.getCurrentQuantity() < 0 || record.getReorderThreshold() <= 0)
            throw new IllegalArgumentException("Invalid stock values");

        record.setProduct(product);
        record.setWarehouse(warehouse);
        record.setLastUpdated(LocalDateTime.now());

        return repository.save(record);
    }

    public StockRecord getStockRecord(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
    }

    public List<StockRecord> getRecordsByProduct(Long productId) {
        return repository.findByProductId(productId);
    }

    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return repository.findByWarehouseId(warehouseId);
    }
}
