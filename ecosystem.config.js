module.exports = {
    apps: [
      {
        name: 'GateWay',
        script: 'java',
        args: ['-jar', './target/cysecinnovations_gateway-0.0.1-SNAPSHOT.jar'],
        instances: 1,
        autorestart: true,
        watch: false,
        max_memory_restart: '1G',
      },
    ],
  };