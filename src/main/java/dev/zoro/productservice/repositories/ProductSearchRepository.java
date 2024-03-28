//package dev.zoro.productservice.repositories;
//
//import dev.zoro.productservice.models.Product;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.UUID;
//
//public interface ProductSearchRepository extends ElasticsearchRepository<Product, UUID> {
//    Page<Product> findAllByTitleContainingOrDescriptionContaining(String query, Pageable pageable);
//}
