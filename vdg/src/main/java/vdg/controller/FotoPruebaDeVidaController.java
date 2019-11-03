package vdg.controller;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.FotoIdentificacion;
import vdg.model.domain.FotoIdentificacion2;
import vdg.model.domain.FotoPruebaDeVida;
import vdg.model.domain.FotoPruebaDeVida2;
import vdg.repository.FotoIdentificacionRepository;
import vdg.repository.FotoPruebaDeVidaRepository;

@RestController
@RequestMapping("/FotoPruebaDeVida")
@CrossOrigin
public class FotoPruebaDeVidaController {

	@Autowired
	private FotoPruebaDeVidaRepository fotoPruebaDeVidaRepo;

	// ESTE METODO DEVUELVE EL STRING DE LA FOTO CON EL ENCABEZADO PARA INDICAR AL
	// FRONT
	// QUE ES UNA IMAGEN
	@GetMapping("/{}")
	public FotoPruebaDeVida2 getProbando() {
		List<FotoPruebaDeVida> fotos = fotoPruebaDeVidaRepo.findAll();
		FotoPruebaDeVida2 foto2 = new FotoPruebaDeVida2();
		for (FotoPruebaDeVida foto : fotos) {
			if (foto.getIdFoto() == 2) {
				foto2.setFoto("data:image/png;base64," + Base64.getEncoder().encodeToString(foto.getFoto()));
				foto2.setIdFoto(1);
				foto2.setIdPruebaDeVida(1);
				return foto2;
			}
		}
		return null;
	}

	@PostMapping("/{idPruebaDeVida}")
	public FotoPruebaDeVida agregarFotoPruebaDeVida(@PathVariable("idPruebaDeVida") int idPruebaDeVida, @RequestBody String foto) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte = decoder.decode(foto.split(",")[1]);
		FotoPruebaDeVida fotoPruebaDeVida = new FotoPruebaDeVida();
		fotoPruebaDeVida.setIdPruebaDeVida(idPruebaDeVida);
		fotoPruebaDeVida.setFoto(decodedByte);

		return fotoPruebaDeVidaRepo.save(fotoPruebaDeVida);
	}

}
