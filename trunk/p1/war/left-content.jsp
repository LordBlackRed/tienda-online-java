<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="crumb_navigation">
	Navigation: <span class="current">Home</span>

</div>


<div class="left_content">
	<div class="title_box">Categories</div>

	<ul class="left_menu">

		<c:forEach items="${requestScope.categorias}" var="categoria">
			<c:set var="noOfRows" value="${noOfRows + 1 }" />

			<c:choose>
				<c:when test="${noOfRows % 2 == 0}">
					<li class="odd"><a href="Categoria?categoria=${categoria.titulo}">${categoria.titulo}</a></li>
				</c:when>
				<c:otherwise>
					<li class="even"><a href="Categoria?categoria=${categoria.titulo}">${categoria.titulo}</a></li>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</ul>

	<div class="title_box">Special Products</div>
	<div class="border_box">
		<div class="product_title">
			<a href="details.html">Motorola 156 MX-VL</a>
		</div>
		<div class="product_img">
			<a href="details.html"><img src="images/laptop.png" alt=""
				title="" border="0" /></a>
		</div>
		<div class="prod_price">
			<span class="reduce">350$</span> <span class="price">270$</span>
		</div>
	</div>

	<div class="title_box">Newsletter</div>
	<div class="border_box">
		<input type="text" name="newsletter" class="newsletter_input"
			value="your email" /> <a href="#" class="join">join</a>
	</div>

	<div class="banner_adds">

		<a href="#"><img src="images/bann2.jpg" alt="" title="" border="0" /></a>
	</div>

</div>
<!-- end of left content -->