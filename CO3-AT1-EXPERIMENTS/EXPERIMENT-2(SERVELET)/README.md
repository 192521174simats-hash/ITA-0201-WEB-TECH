# Experiment 2 - Servlet-Based Student Result Processing

## Files in this zip
- `webapp/index.html`        → the submission form (this is your web root content)
- `webapp/WEB-INF/web.xml`   → deployment descriptor
- `ResultServlet.java`       → the servlet source (kept at the top level; place it correctly per below)

## Where to put these in your Eclipse project (Dynamic Web Project)

```
YourProject
└── src
    └── main
        ├── java
        │   └── (your package, e.g. eglog)
        │       └── ResultServlet.java   <-- put it HERE
        └── webapp
            ├── META-INF
            ├── WEB-INF
            │   ├── lib
            │   └── web.xml              <-- replace with the one in this zip
            └── index.html               <-- replace with the one in this zip
```

**Important:** `index.html` must sit directly inside `webapp`, as a sibling of
`WEB-INF` — never inside `WEB-INF` itself. Files inside `WEB-INF` are never
served directly to the browser.

**If your servlet class is inside a package** (e.g. `eglog`, as shown in your
Eclipse project), add this as the very first line of `ResultServlet.java`:

```java
package eglog;
```

## Running it

1. Copy the files into place as shown above.
2. Right-click your project → Run As → Run on Server → pick Tomcat v11.0 → Finish.
3. Go to `http://localhost:8080/YourProjectName/index.html` (not `/ResultServlet` directly).
4. Fill in the form and click Submit — the servlet computes Total, Average,
   Highest Mark, and Pass/Fail, and renders it with a matching gradient background.

## Notes
- Uses `jakarta.servlet.*` imports — required for Tomcat 11 (Tomcat 10+ uses Jakarta EE, not the old `javax.servlet`).
- All request data (name, marks, total, average, etc.) is kept in **local
  variables inside `doPost()`** only, so concurrent requests from different
  students never interfere with each other.
- Marks outside 0–100, or missing fields, are rejected with a clear validation message.
