select ccliente, vapellido, vnombre 
from dem_cliente
where (:ccliente = 0 or ccliente = :ccliente)
	and (
		(:filter = '' or vnombre like :filter)
		or (:filter = '' or vapellido like :filter)
	)
order by ccliente 
