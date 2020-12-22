package pl.wpulik;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.wpulik.model.DailySimulation;
import pl.wpulik.model.Simulation;
import pl.wpulik.service.EpidemicCourse;

@SpringBootApplication
public class Covid19EpidemicDevelopmentRestApplication {

	/*
	public static void main(String[] args) {
		SpringApplication.run(Covid19EpidemicDevelopmentRestApplication.class, args);
	}
	*/
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Covid19EpidemicDevelopmentRestApplication.class, args);
		
		EpidemicCourse ec = ctx.getBean(EpidemicCourse.class);
		/*
		 * public Simulation(String name, Long populationSize, Long initialInfected, BigDecimal transmissionFactor,
			BigDecimal mortalityRate, Integer recoveryTime, Integer deadTime, Integer durationTime)
		 */
		Simulation simul = new Simulation("Polska", 38000000L, 456L, BigDecimal.valueOf(1.36), BigDecimal.valueOf(0.02636), 7, 5, 35);
		ec.firstDay(simul);
		ec.covidCourse();
		for(DailySimulation ds : ec.dailyCovidCourse()) {
			System.out.println(ds.toString());
		}
	}

}
