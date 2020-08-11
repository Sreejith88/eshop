package com.eshop.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.EShopConstants;
import com.eshop.dto.ProductDto;
import com.eshop.model.Product;
import com.eshop.repository.ProductRepository;

/**
 * <p>
 * The service implementation class for product services.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final ProductRepository productRepository;

	public ProductServiceImpl(final ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveProduct(final ProductDto productDto, final MultipartFile file, final String username) {
		final Product product = new Product();
		productDto.setCreatedBy(username);
		if (!file.getOriginalFilename().isEmpty()) {
			final UUID uuid = UUID.randomUUID();
			final String fileName = uuid.toString() + EShopConstants.DOT + file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf(EShopConstants.DOT) + 1);
		
			// save the file on the local file system
			try {
				final Path path = Paths.get(EShopConstants.ROOT);
				Files.copy(file.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
				productDto.setProductImagePath(fileName);
			} catch (final IOException e) {
				LOGGER.error("Exception in saveProduct() @ ProductServiceImpl.class");
			}
		}
		try {
			BeanUtils.copyProperties(product, productDto);
		} catch (final IllegalAccessException e) {
			LOGGER.error("IllegalAccessException in saveProduct() @ ProductServiceImpl.class");
		} catch (final InvocationTargetException e) {
			LOGGER.error("InvocationTargetException in saveProduct() @ ProductServiceImpl.class");
		}
		this.productRepository.save(product);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductDto> findAllProducts() {
		final List<Product> productList = this.productRepository.findAll();
		List<ProductDto> productDtoList = new ArrayList<>();
		productDtoList = productList.stream().map(product -> {
			final ProductDto productDto = new ProductDto();
			try {
				BeanUtils.copyProperties(productDto, product);
				productDto.setProductImagePath(
						EShopConstants.ROOT.concat(Objects.toString(product.getProductImagePath(), "")));
			} catch (final IllegalAccessException e) {
				LOGGER.error("IllegalAccessException in findAllProducts() @ ProductServiceImpl.class");
			} catch (final InvocationTargetException e) {
				LOGGER.error("InvocationTargetException in findAllProducts() @ ProductServiceImpl.class");
			}
			return productDto;
		}).collect(Collectors.toList());

		return productDtoList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductDto getProductById(final long id) {
		final Optional<Product> optional = this.productRepository.findById(id);
		Product product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException(" Product not found for id :: " + id);
		}
		final ProductDto productDto = new ProductDto();
		try {
			BeanUtils.copyProperties(productDto, product);
		} catch (final IllegalAccessException e) {
			LOGGER.error("IllegalAccessException in getProductById() @ ProductServiceImpl.class");
		} catch (final InvocationTargetException e) {
			LOGGER.error("InvocationTargetException in getProductById() @ ProductServiceImpl.class");
		}
		return productDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteProductById(final long id) {
		this.productRepository.deleteById(id);
	}

}
