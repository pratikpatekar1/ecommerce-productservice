package dev.zoro.productservice.controllers;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.dtos.SearchRequestDto;
import dev.zoro.productservice.dtos.SortParameter;
//import dev.zoro.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
//    private SearchService searchService;
//    public SearchController(SearchService searchService){
//        this.searchService = searchService;
//    }
    @PostMapping
    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto){
        List<SortParameter> sortParameters = searchRequestDto.getSortByParameters();
        Sort sort;
        if(sortParameters.get(0).getSortType().equals("ASC")){
            sort = Sort.by(sortParameters.get(0).getParameterName()).ascending();
        }else{
            sort = Sort.by(sortParameters.get(0).getParameterName()).descending();
        }
        for(int i=1;i< sortParameters.size();i++){
            if(sortParameters.get(i).getSortType().equals("ASC")){
                sort = sort.and(Sort.by(sortParameters.get(i).getParameterName()).ascending());
            }else{
                sort = sort.and(Sort.by(sortParameters.get(i).getParameterName()).descending());
            }
        }

        Pageable pageable = PageRequest.of(searchRequestDto.getPageNumber(), searchRequestDto.getPageSize(), sort);

//        return searchService.searchProducts(searchRequestDto.getQuery(), pageable);
        return null;
    }
}
