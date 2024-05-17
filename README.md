# PactFlow Contract Testing

This repository demonstrates contract testing using PactFlow. It includes a consumer application and a provider application, with CI/CD examples for both consumer-driven and bidirectional contract testing.

## Table of Contents

- [Overview](#overview)
- [Setup](#setup)
- [Consumer Application](#consumer-application)
- [Provider Application](#provider-application)
- [Running the Applications](#running-the-applications)
- [Contract Testing](#contract-testing)
    - [Consumer-Driven Contract Testing](#consumer-driven-contract-testing)
    - [Bidirectional Contract Testing](#bidirectional-contract-testing)
- [CI/CD Integration](#cicd-integration)
    - [Consumer CI/CD](#consumer-cicd)
    - [Provider CI/CD](#provider-cicd)
- [Resources](#resources)

## Overview

Contract testing ensures that services (consumer and provider) can communicate with each other correctly. PactFlow is a platform that helps manage these contract tests, providing a way to publish and verify contracts.

## Setup

./gradlew clean build

## Consumer Application

The consumer application simulates a client that makes requests to the provider. It uses Pact to define expected interactions with the provider.
The consumer calls ```/books``` endpoint of the provider.

 - ```BookClientConsumerTest``` is the consumer test  written to generate contracts with the provider
 - ```./gradlew runConsumerTest``` for running the test using gradle command

## Provider Application

The provider application simulates a service that the consumer interacts with. It uses Pact to verify that it meets the consumer's expectations.

- ```BookProviderTest``` is the test written to verify the contract against the consumer and publish the results to PactFlow.
- To run the above test set environment variables for **PACT_FLOW_BASE_URL** and **PACT_FLOW_TOKEN**.
- The test will fetch the latest contract from PactFlow and verify the contract .

## Contract Testing

### Consumer-Driven Contract Testing

In consumer-driven contract testing, the consumer defines the interactions and expectations. These are then shared with the provider to ensure it meets the expectations.

### Bidirectional Contract Testing

Bidirectional contract testing uses PactFlow to automatically infer the provider's capabilities and verify against the consumer's expectations.

## CI/CD Integration

### Consumer CI/CD 

`pact-consumer.yml` is the workflow to trigger the following set of steps:

1. [x] Run the consumer test to generate the contract. `./gradlew runConsumerTest`
2. [x] Publish the contract using pact cli
3. [x] Can-I-Deploy
4. [x] Record Deployment

### Provider CI/CD 

`pact-provider.yml` is the workflow to trigger the following set of steps for consumer driven contract testing:

1. [x] Run the provider test pact verification. `./gradlew runProviderTest`
2. [x] Can-I-Deploy
3. [x] Record Deployment

`pact-bidirectional-provider.yml` is the workflow to trigger the following set of steps for bidirectional contract testing:

1. [x] Publish provider contract
2. [x] Can-I-Deploy
3. [x] Record Deployment

## Resources

[Consumer Driven Contract Testing ](https://pactflow.io/what-is-consumer-driven-contract-testing/)
[BiDirectional Contract Testing ](https://pactflow.io/bi-directional-contract-testing/)