public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
    Optional<StockRecord> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
    List<StockRecord> findByProductId(Long productId);
    List<StockRecord> findByWarehouseId(Long warehouseId);
}
