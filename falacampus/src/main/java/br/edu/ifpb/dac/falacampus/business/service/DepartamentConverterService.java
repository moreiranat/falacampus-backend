package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;

@Service
public interface DepartamentConverterService {
	
	public List<DepartamentDto> departamentToDTO(List<Departament> entities);
	public Departament dtoToDepartament(DepartamentDto dto);
	public DepartamentDto departamentToDTO(Departament entity);

}
