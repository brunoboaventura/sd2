<?php
	if (count(get_defined_vars()[_GET])) {
		$vars = get_defined_vars()[_GET];
		$metodo = "GET:";
	} elseif (count(get_defined_vars()[_POST])) {
		$vars = get_defined_vars()[_POST];
		$metodo = "POST:";
	} else {
	       echo "Nenhum parâmetro foi passado. ";
	}

	$soma = 0;

	foreach ($vars as $v) {
		$soma += intval($v);
	}

	echo $metodo . "A soma é: ";
	echo $soma;
?>