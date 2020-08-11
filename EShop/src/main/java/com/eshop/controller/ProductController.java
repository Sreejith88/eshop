package com.eshop.controller;

import javax.naming.spi.DirStateFactory.Result;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.EShopConstants;
import com.eshop.dto.ProductDto;
import com.eshop.service.ProductService;

/**
 * <p>
 * The controller class for product related activities.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	/**
	 * <p>
	 * Service method for save or update product details.
	 * </p>
	 * 
	 * @param productDto the {@link ProductDto} instance contains product details.
	 * @param result the {@link BindingResult} instance.
	 * @param model the {@link Result} instance.
	 * @param authentication {@link Authentication} instance.
	 * @param file the {@link MultipartFile} instance contains uploaded file.
	 * @param updateProduct boolean value to check update or not.
	 * @return the render page name.
	 */
	@PostMapping("/saveProduct")
	public String saveOrUpdateProduct(@ModelAttribute("product") @Valid final ProductDto productDto, final BindingResult result,
			final Model model, final Authentication authentication, @RequestParam("file") final MultipartFile file,
			@RequestParam(value = "update", defaultValue = "false") final boolean updateProduct) {
		// check for the field validation.
		if (result.hasErrors()) {
			if (updateProduct)
				return "update_product";
			else
				return "new_product";
		}

		// check if file is empty
		if (!updateProduct && file.isEmpty()) {
			model.addAttribute(EShopConstants.MESSAGE, EShopConstants.PRODUCT_SELECT_MESSAGE);
			return "new_product";
		}

		this.productService.saveProduct(productDto, file, authentication.getName());
		model.addAttribute(EShopConstants.LIST_PRODUCTS, this.productService.findAllProducts());
		return "product_list";
	}

	/**
	 * <p>
	 * Service method for get all product details.
	 * </p>
	 * 
	 * @param model the {@link Model} instance.
	 * @param dashboardRoute boolean to check whether details for dashboard or not.
	 * @return the render page name.
	 */
	@GetMapping("/getAllProducts")
	public String viewProductList(final Model model,
			@RequestParam(value = "dashboard", defaultValue = "false") final boolean dashboardRoute) {
		model.addAttribute(EShopConstants.LIST_PRODUCTS, this.productService.findAllProducts());
		if (dashboardRoute) {
			return "dashboard";
		}
		return "product_list";
	}

	/**
	 * <p>
	 * Service method for getting product details by using id for update.
	 * </p>
	 * 
	 * @param id the product id
	 * @param model the {@link Model} instance.
	 * @return the render page name.
	 */
	@GetMapping("/showProductFormForUpdate/{id}")
	public String showProductFormForUpdate(@PathVariable(value = "id") final long id, final Model model) {
		model.addAttribute(EShopConstants.PRODUCT, this.productService.getProductById(id));
		return "update_product";
	}

	/**
	 * <p>
	 * Method for create model attribute for new product page.
	 * </p>
	 * 
	 * @param model the {@link Model} instance.
	 * @return the render page name.
	 */
	@GetMapping("/showNewProductForm")
	public String viewNewProductForm(final Model model) {
		// create model attribute to bind form data
		final ProductDto product = new ProductDto();
		model.addAttribute(EShopConstants.PRODUCT, product);
		return "new_product";
	}

	/**
	 * <p>
	 * Service method for delete the product by using id.
	 * </p>
	 * @param id the product id
	 * @return the render page name.
	 */
	@GetMapping("/deleteProduct/{id}")
	public String deleteProductById(@PathVariable(value = "id") final long id) {
		this.productService.deleteProductById(id);
		return "redirect:/getAllProducts";
	}
}
