import * as cdk from 'aws-cdk-lib'; // Core CDK library for defining AWS resources
import { Construct } from 'constructs'; // Base class for all CDK constructs (building blocks)
import * as dynamodb from 'aws-cdk-lib/aws-dynamodb'; // Import DynamoDB module for NoSQL database
import * as sqs from 'aws-cdk-lib/aws-sqs'; // Import SQS (Simple Queue Service) module for message queuing
import * as s3 from 'aws-cdk-lib/aws-s3'; // Import S3 (Simple Storage Service) module for file storage
import * as sns from 'aws-cdk-lib/aws-sns'; // Import SNS (Simple Notification Service) module for notifications

// This class defines the AWS infrastructure stack so we can manage all our resources in one place.
// We can think of it as a blueprint for all the AWS resources my app needs.
export class InfrastructureStack extends cdk.Stack {
  // The constructor is where I define (create) my AWS resources.
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props); // Call the parent class constructor to initialize the stack

    // 1. Create a DynamoDB table for inventory data
    const inventoryTable = new dynamodb.Table(this, 'InventoryTable', {
      partitionKey: { name: 'id', type: dynamodb.AttributeType.STRING }, // Each item will have a unique 'id' and be a string
      billingMode: dynamodb.BillingMode.PAY_PER_REQUEST, // Only pay for what you use (PER_REQUEST billing)
    });

    // 2. Create a DynamoDB table for shipment data
    const shipmentTable = new dynamodb.Table(this, 'ShipmentTable', {
      partitionKey: { name: 'id', type: dynamodb.AttributeType.STRING },
      billingMode: dynamodb.BillingMode.PAY_PER_REQUEST,
    });

    // 3. Create an SQS queue for inventory-related messages (for async processing)
    const inventoryQueue = new sqs.Queue(this, 'InventoryQueue');

    // 4. Create an SQS queue for shipment-related messages
    const shipmentQueue = new sqs.Queue(this, 'ShipmentQueue');

    // 5. Create an S3 bucket to store reports or files
    const reportBucket = new s3.Bucket(this, 'ReportBucket');

    // 6. Create an SNS topic for sending notifications (email, SMS, etc.)
    const notificationTopic = new sns.Topic(this, 'NotificationTopic');
  }
}