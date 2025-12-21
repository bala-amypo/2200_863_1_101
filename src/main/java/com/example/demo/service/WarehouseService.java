@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository repository;

    public Warehouse createWarehouse(Warehouse warehouse) {
        if (warehouse.getLocation() == null || warehouse.getLocation().isBlank())
            throw new IllegalArgumentException("Location required");

        warehouse.setCreatedAt(LocalDateTime.now());
        return repository.save(warehouse);
    }

    public Warehouse getWarehouse(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }

    public List<Warehouse> getAllWarehouses() {
        return repository.findAll();
    }
}
