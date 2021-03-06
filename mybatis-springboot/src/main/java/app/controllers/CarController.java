package app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.mappers.ICarMapper;
import app.models.Car;

@RestController
@RequestMapping("/api/cars")
public class CarController {

	private ICarMapper carMapper;
	
	public CarController(ICarMapper carMapper) {
		this.carMapper = carMapper;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> getCar(@PathVariable long id) {
		Car car = carMapper.findById(id);
		if (car != null) {
			return new ResponseEntity<Car>(car, HttpStatus.OK);
		}
		else return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Car>> getCars(){
		return new ResponseEntity<Iterable<Car>>(carMapper.findAll(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Car> createCar(@RequestBody Car car) {
		if (car != null) {
			carMapper.save(car);
			return new ResponseEntity<Car>(car, HttpStatus.CREATED);
		}
		else return new ResponseEntity<Car>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Car> deleteCar(@PathVariable long id) {
		if (carMapper.findById(id) != null) {
			carMapper.deleteById(id);
			return new ResponseEntity<Car>(HttpStatus.OK);
		}
		else return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Car> deleteCar(@PathVariable long id, @RequestBody Car car) {
		if (carMapper.findById(id) != null) {
			carMapper.updateById(id, car);
			return new ResponseEntity<Car>(car,HttpStatus.OK);
		}
		else {
			carMapper.save(car);
			return new ResponseEntity<Car>(car, HttpStatus.CREATED);
		}
	}
}
