package com.eshop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eshop.dto.ProductDto;

/**
 * <p>
 * The interface for product services.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
public interface ProductService {

	/**
	 * <p>
	 * Service method for save or update product details.
	 * </p>
	 * 
	 * @param productDto the {@link ProductDto} instance to be saved
	 * @param file       the {@link MultipartFile} file.
	 * @param username   the user name for update createdBy.
	 */
	void saveProduct(final ProductDto productDto, final MultipartFile file, final String username);

	/**
	 * <p>
	 * Service method for get all product details.
	 * </p>
	 * 
	 * @return the list of {@link ProductDto} instance.
	 */
	List<ProductDto> findAllProducts();

	/**
	 * <p>
	 * Service method for getting product details by using id.
	 * </p>
	 * 
	 * @param id the product id
	 * @return the {@link ProductDto} instance contains product details.
	 */
	ProductDto getProductById(long id);

	/**
	 * <p>
	 * Service method for delete the product by using id.
	 * </p>
	 * 
	 * @param id the product id
	 */
	void deleteProductById(long id);

}
