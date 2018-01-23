package hu.holdinarms.example;

import hu.holdinarms.example.resources.Resource;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import org.skife.jdbi.v2.DBI;

import hu.holdinarms.example.dao.MyDao;
import hu.holdinarms.example.health.DatabaseHealthCheck;
import io.dropwizard.Application;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MainService extends Application<MainConfiguration> {

  public static void main(String[] args) throws Exception {
    new MainService().run(args);
  }

  @Override
  public void initialize(Bootstrap<MainConfiguration> bootstrap) {
    bootstrap.addBundle(new MigrationsBundle<MainConfiguration>() {
      @Override
      public PooledDataSourceFactory getDataSourceFactory(final MainConfiguration configuration) {
        return configuration.getDataSourceFactory();
      }
    });
    bootstrap.addBundle(new AssetsBundle("/assets/"));
  }

  @Override
  public void run(MainConfiguration configuration, Environment environment) throws Exception {
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
    final MyDao dao = jdbi.onDemand(MyDao.class);

    environment.jersey().register(new Resource(dao));

    environment.healthChecks().register("health",
        new DatabaseHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));
  }

}
