package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.EmpleadoEntity;
import com.Residencia.proyecto.restaurant.Entity.UserEntity;
import com.Residencia.proyecto.restaurant.Repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Residencia.proyecto.restaurant.Services.UserService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping()
    public CustomResponse getUsers() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(userDao.findAll());
        return customResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> verDetallesDelEmpleado(@PathVariable Long id) {
        UserEntity usuario = userDao.findById(id).get();

        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping()
    public CustomResponse guardarUser(@RequestBody UserEntity user) {
        CustomResponse customResponse = new CustomResponse();
        userDao.save(user);
        customResponse.setData("creado");
        return customResponse;
    }
//	
//	@PutMapping("/instructores/{id}")
//	public ResponseEntity<Instructor> actualizarInstructor(@PathVariable Long id,@Valid @RequestBody Instructor instructorDetallles){
//		Instructor instructor = instructorRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Instructor con el ID : " + id + " no encontrado"));
//		
//		instructor.setEmail(instructorDetallles.getEmail());
//		Instructor instructorActualizado = instructorRepository.save(instructor);
//		return ResponseEntity.ok(instructorActualizado);
//	}
//	
//	@DeleteMapping("/instructores/{id}")
//	public Map<String, Boolean> eliminarInstructor(@PathVariable Long id){
//		Instructor instructor = instructorRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Instructor con el ID : " + id + " no encontrado"));
//		
//		instructorRepository.delete(instructor);
//		Map<String,Boolean> respuesta = new HashMap<>();
//		respuesta.put("Instructor eliminado", Boolean.TRUE);
//		return respuesta;
//	}
}
