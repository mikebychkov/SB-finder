echo "Launching $BUILD_NAME IN AMAZON ECS"

ecs-cli configure profile --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY
ecs-cli configure --region eu-central-1 --cluster sb
ecs-cli compose --file docker/aws-compose.yml up
