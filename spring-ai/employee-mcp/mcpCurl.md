## 1. Protocol Handshake

These calls establish the MCP session and discover capabilities.

### 1.1 Initialize

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 1,
    "method": "initialize",
    "params": {
      "protocolVersion": "2024-11-05",
      "capabilities": {},
      "clientInfo": {
        "name": "curl-client",
        "version": "1.0"
      }
    }
  }' | jq .
```


### 1.2 List Tools

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 2,
    "method": "tools/list",
    "params": {}
  }' | jq .
```
### 1.3 List Resources

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 3,
    "method": "resources/list",
    "params": {}
  }' | jq .
```

### 1.4 List Prompts

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 4,
    "method": "prompts/list",
    "params": {}
  }' | jq .
```

## 2. TOOLS

All tool calls use `method: "tools/call"` with `params.name` and `params.arguments`.

---

### Tool 1 — getEmployees (Paginated)

**Page 0 (first 10 employees):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 10,
    "method": "tools/call",
    "params": {
      "name": "getEmployees",
      "arguments": { "pageNumber": 0, "pageSize": 10 }
    }
  }' | jq .
```

**Page 1 (next 10 employees):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 11,
    "method": "tools/call",
    "params": {
      "name": "getEmployees",
      "arguments": { "pageNumber": 1, "pageSize": 10 }
    }
  }' | jq .
```

**Large page (all 100 at once):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 12,
    "method": "tools/call",
    "params": {
      "name": "getEmployees",
      "arguments": { "pageNumber": 0, "pageSize": 100 }
    }
  }' | jq '.result.content[0].text | fromjson | .totalElements'
```

---

### Tool 2 — getEmployeesUnderManager

**Get direct reports of Priya Mehta (VP Engineering, id=2):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 20,
    "method": "tools/call",
    "params": {
      "name": "getEmployeesUnderManager",
      "arguments": { "managerId": 2 }
    }
  }' | jq .
```

**Error case — non-existent manager:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 23,
    "method": "tools/call",
    "params": {
      "name": "getEmployeesUnderManager",
      "arguments": { "managerId": 999 }
    }
  }' | jq .
```

---

### Tool 3 — getEmployeesByCity

**Mumbai:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 30,
    "method": "tools/call",
    "params": {
      "name": "getEmployeesByCity",
      "arguments": { "city": "Mumbai", "pageNumber": 0, "pageSize": 20 }
    }
  }' | jq .
```

**Case-insensitive (lowercase):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 33,
    "method": "tools/call",
    "params": {
      "name": "getEmployeesByCity",
      "arguments": { "city": "mUMbai", "pageNumber": 0, "pageSize": 10 }
    }
  }' | jq .
```

### Tool 4 — getEmployeesByCountry

**India:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 40,
    "method": "tools/call",
    "params": {
      "name": "getEmployeesByCountry",
      "arguments": { "country": "India", "pageNumber": 0, "pageSize": 20 }
    }
  }' | jq .
```

### Tool 5 — searchEmployees

**Search for "Architect":**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 50,
    "method": "tools/call",
    "params": {
      "name": "searchEmployees",
      "arguments": { "keyword": "Architect" }
    }
  }' | jq .
```

**Search for "Senior" engineers:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 51,
    "method": "tools/call",
    "params": {
      "name": "searchEmployees",
      "arguments": { "keyword": "Senior" }
    }
  }' | jq .
```

**Search by last name "Sharma":**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 52,
    "method": "tools/call",
    "params": {
      "name": "searchEmployees",
      "arguments": { "keyword": "Sharma" }
    }
  }' | jq .
```

**Search by department "Finance":**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 53,
    "method": "tools/call",
    "params": {
      "name": "searchEmployees",
      "arguments": { "keyword": "Finance" }
    }
  }' | jq .
```

**Search by designation "Director":**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 54,
    "method": "tools/call",
    "params": {
      "name": "searchEmployees",
      "arguments": { "keyword": "Director" }
    }
  }' | jq .
```

---

### Tool 6 — getEmployee

**CEO (id=1):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 60,
    "method": "tools/call",
    "params": {
      "name": "getEmployee",
      "arguments": { "employeeId": 1 }
    }
  }' | jq .
```

**Individual contributor (id=42):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 61,
    "method": "tools/call",
    "params": {
      "name": "getEmployee",
      "arguments": { "employeeId": 42 }
    }
  }' | jq .
```

**Error — not found:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 62,
    "method": "tools/call",
    "params": {
      "name": "getEmployee",
      "arguments": { "employeeId": 999 }
    }
  }' | jq .
```

---

### Tool 7 — getManagers

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 70,
    "method": "tools/call",
    "params": {
      "name": "getManagers",
      "arguments": {}
    }
  }' | jq .
```

**Count how many managers:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 71,
    "method": "tools/call",
    "params": { "name": "getManagers", "arguments": {} }
  }' | jq '.result.content[0].text | fromjson | length'
```

---

### Tool 8 — getDepartments

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 80,
    "method": "tools/call",
    "params": {
      "name": "getDepartments",
      "arguments": {}
    }
  }' | jq .
```

---

### Tool 9 — getCountries

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 90,
    "method": "tools/call",
    "params": {
      "name": "getCountries",
      "arguments": {}
    }
  }' | jq .
```

---

### Tool 10 — getCities

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 100,
    "method": "tools/call",
    "params": {
      "name": "getCities",
      "arguments": {}
    }
  }' | jq .
```

---

### Tool 11 — getEmployeesByDepartment

**Engineering:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 110,
    "method": "tools/call",
    "params": {
      "name": "getEmployeesByDepartment",
      "arguments": { "department": "Engineering", "pageNumber": 0, "pageSize": 20 }
    }
  }' | jq .
```

**HR:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 111,
    "method": "tools/call",
    "params": {
      "name": "getEmployeesByDepartment",
      "arguments": { "department": "HR", "pageNumber": 0, "pageSize": 10 }
    }
  }' | jq .
```

**Security:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 112,
    "method": "tools/call",
    "params": {
      "name": "getEmployeesByDepartment",
      "arguments": { "department": "Security", "pageNumber": 0, "pageSize": 10 }
    }
  }' | jq .
```

---

### Tool 12 — countEmployees

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 120,
    "method": "tools/call",
    "params": {
      "name": "countEmployees",
      "arguments": {}
    }
  }' | jq .
```

---

### Tool 13 — countEmployeesByCountry

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 130,
    "method": "tools/call",
    "params": {
      "name": "countEmployeesByCountry",
      "arguments": {}
    }
  }' | jq .
```

---

### Tool 14 — countEmployeesByDepartment

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 140,
    "method": "tools/call",
    "params": {
      "name": "countEmployeesByDepartment",
      "arguments": {}
    }
  }' | jq .
```

---

### Tool 15 — getOrganizationHierarchy

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 150,
    "method": "tools/call",
    "params": {
      "name": "getOrganizationHierarchy",
      "arguments": {}
    }
  }' | jq .
```

**Show only the top level (CEO + VPs):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 151,
    "method": "tools/call",
    "params": { "name": "getOrganizationHierarchy", "arguments": {} }
  }' | jq '.result.content[0].text | fromjson | { name, designation, reports: [.directReports[].name] }'
```

---

## 2. RESOURCES

All resource reads use `method: "resources/read"` with `params.uri`.

---

### employees://all

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 200,
    "method": "resources/read",
    "params": { "uri": "employees://all" }
  }' | jq .
```

**Count employees from resource:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{"jsonrpc":"2.0","id":201,"method":"resources/read","params":{"uri":"employees://all"}}' \
  | jq '.result.contents[0].text | fromjson | length'
```

---

### employees://countries

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 210,
    "method": "resources/read",
    "params": { "uri": "employees://countries" }
  }' | jq .
```

---

### employees://cities

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 220,
    "method": "resources/read",
    "params": { "uri": "employees://cities" }
  }' | jq .
```

---

### employees://departments

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 230,
    "method": "resources/read",
    "params": { "uri": "employees://departments" }
  }' | jq .
```

---

### employees://organization

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 240,
    "method": "resources/read",
    "params": { "uri": "employees://organization" }
  }' | jq .
```

---

### employees://managers

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 250,
    "method": "resources/read",
    "params": { "uri": "employees://managers" }
  }' | jq .
```

---

### employees://statistics

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 260,
    "method": "resources/read",
    "params": { "uri": "employees://statistics" }
  }' | jq .
```

---

### employees://engineering

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 270,
    "method": "resources/read",
    "params": { "uri": "employees://engineering" }
  }' | jq .
```

---

### employees://sales

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 280,
    "method": "resources/read",
    "params": { "uri": "employees://sales" }
  }' | jq .
```

---

### employees://finance

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 290,
    "method": "resources/read",
    "params": { "uri": "employees://finance" }
  }' | jq .
```

---

### employees://hr

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 300,
    "method": "resources/read",
    "params": { "uri": "employees://hr" }
  }' | jq .
```

---

### employees://marketing

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 310,
    "method": "resources/read",
    "params": { "uri": "employees://marketing" }
  }' | jq .
```

---

### employees://support

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 320,
    "method": "resources/read",
    "params": { "uri": "employees://support" }
  }' | jq .
```

---

### employees://india

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 330,
    "method": "resources/read",
    "params": { "uri": "employees://india" }
  }' | jq .
```

---

### employees://usa

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 340,
    "method": "resources/read",
    "params": { "uri": "employees://usa" }
  }' | jq .
```

---

## 4. PROMPTS

All prompt calls use `method: "prompts/get"` with `params.name` and `params.arguments`.

---

### Prompt 1 — employee-summary

**Summary for CEO (id=1):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 400,
    "method": "prompts/get",
    "params": {
      "name": "employee-summary",
      "arguments": { "employeeId": "1" }
    }
  }' | jq .
```

**Summary for an engineer (id=42):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 401,
    "method": "prompts/get",
    "params": {
      "name": "employee-summary",
      "arguments": { "employeeId": "42" }
    }
  }' | jq .
```

---

### Prompt 2 — department-summary

**Engineering:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 410,
    "method": "prompts/get",
    "params": {
      "name": "department-summary",
      "arguments": { "department": "Engineering" }
    }
  }' | jq .
```

**Sales:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 411,
    "method": "prompts/get",
    "params": {
      "name": "department-summary",
      "arguments": { "department": "Sales" }
    }
  }' | jq .
```

---

### Prompt 3 — country-summary

**India:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 420,
    "method": "prompts/get",
    "params": {
      "name": "country-summary",
      "arguments": { "country": "India" }
    }
  }' | jq .
```

**Germany:**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 421,
    "method": "prompts/get",
    "params": {
      "name": "country-summary",
      "arguments": { "country": "Germany" }
    }
  }' | jq .
```

---

### Prompt 4 — manager-summary

**Vikram Rao (Manager, id=8):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 430,
    "method": "prompts/get",
    "params": {
      "name": "manager-summary",
      "arguments": { "managerId": "8" }
    }
  }' | jq .
```

**Sarah Thompson (VP Engineering, id=2):**
```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 431,
    "method": "prompts/get",
    "params": {
      "name": "manager-summary",
      "arguments": { "managerId": "2" }
    }
  }' | jq .
```

---

### Prompt 5 — hr-assistant (No arguments)

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 440,
    "method": "prompts/get",
    "params": {
      "name": "hr-assistant",
      "arguments": {}
    }
  }' | jq .
```

---

### Prompt 6 — employee-search-assistant (No arguments)

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 450,
    "method": "prompts/get",
    "params": {
      "name": "employee-search-assistant",
      "arguments": {}
    }
  }' | jq .
```

---

### Prompt 7 — organization-explorer (No arguments)

```bash
curl -s -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{
    "jsonrpc": "2.0",
    "id": 460,
    "method": "prompts/get",
    "params": {
      "name": "organization-explorer",
      "arguments": {}
    }
  }' | jq .
```

---

