
# ClusteredData Warehouse

A data warehouse for Bloomberg to analyze FX deals and persist them into DB

## Requirements
To run this application: 
- install docker. 
- install make. 
- clone git repository.


## Run Locally via docker or make

Clone the project

```bash
  git clone https://github.com/IsiomaAghaulor/clustered_data_warehouse.git
```

Go to the project directory

```bash
  cd clustered_Data_Warehouse
```

Run Via Make

```bash
  make dev
```

Run Via Docker
```bash
  - docker compose build
  - docker compose up 
```


## API Reference
All Urls are relative to:
- for `docker` http://localhost:8081
- for running it via `terminal` http://localhost:8080
#### Save FX Deal

```http
  POST /fxDealProcessor/api/v1/process-fxDeal
```

| Method   | HTTP Request                            | Description                      |
|:---------|:----------------------------------------|:---------------------------------|
| `POST`   | `fxDealProcessor/api/v1/process-fxDeal` | Endpoint for persisting FX deals |

#### Sample Request
```
{
  "dealUniqueId": "PQ-ytd99",
  "orderingCurrency": "USD",
  "toCurrency": "NGN",
  "dealAmount": 10000.00,
  "dealTimeStamp": "2023-09-16T12:02:24.680682"
}
```
#### Sample Response
```
{
    "responseCode": "00",
    "responseMsg": "FX DEAL INFO PERSISTED SUCCESSFULLY",
    "responseDetails":
    {
      "dealUniqueId": "PQ-ytd99",
     "orderingCurrency": "USD",
     "toCurrency": "NGN",
     "dealAmount": 10000.00,
     "dealTimeStamp": "2023-09-16T12:02:24.680682"
    }
}
```


