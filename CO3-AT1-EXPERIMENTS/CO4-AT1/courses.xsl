<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes"/>
<xsl:template match="/">
<html><head><meta charset="UTF-8"/><meta name="viewport" content="width=device-width, initial-scale=1.0"/><title>Semester Workload Dashboard</title><link rel="stylesheet" href="style.css"/></head>
<body><div class="page">
<header class="header"><div><div class="small-title">UNIVERSITY ACADEMIC ANALYTICS</div><h1>Semester Workload Dashboard</h1><p>XML • XPath • XSLT Course Analysis</p></div><div class="status">● ANALYSIS READY</div></header>
<section class="cards">
<div class="card"><span class="card-icon">◈</span><span class="card-label">TOTAL COURSES</span><strong><xsl:value-of select="count(courses/course)"/></strong><small>Academic courses</small></div>
<div class="card"><span class="card-icon">◎</span><span class="card-label">TOTAL STUDENTS</span><strong><xsl:value-of select="sum(courses/course/students)"/></strong><small>Across all courses</small></div>
<div class="card"><span class="card-icon">◇</span><span class="card-label">THEORY COURSES</span><strong><xsl:value-of select="count(courses/course[type='Theory'])"/></strong><small>Theory subjects</small></div>
<div class="card"><span class="card-icon">▣</span><span class="card-label">PRACTICAL COURSES</span><strong><xsl:value-of select="count(courses/course[type='Practical'])"/></strong><small>Laboratory subjects</small></div>
</section>
<section class="panel"><div class="panel-header"><div><span class="section-tag">XML DATASET</span><h2>Course Records</h2></div><div class="record-count"><xsl:value-of select="count(courses/course)"/> Records</div></div>
<div class="table-container"><table><thead><tr><th>ID</th><th>COURSE CODE</th><th>COURSE NAME</th><th>FACULTY</th><th>STUDENTS</th><th>CREDITS</th><th>TYPE</th></tr></thead><tbody>
<xsl:for-each select="courses/course"><tr><td><span class="id-badge"><xsl:value-of select="@id"/></span></td><td class="code"><xsl:value-of select="code"/></td><td class="course-name"><xsl:value-of select="name"/></td><td><xsl:value-of select="faculty"/></td><td><span class="student-badge"><xsl:value-of select="students"/></span></td><td><xsl:value-of select="credits"/></td><td><span class="type-badge"><xsl:value-of select="type"/></span></td></tr></xsl:for-each>
</tbody></table></div></section>
<section class="panel"><div class="panel-header"><div><span class="section-tag">XPATH ANALYSIS</span><h2>Data Selection Results</h2></div></div>
<div class="query-box"><div class="query-title"><span>01</span>Students greater than 50</div><div class="xpath-code">/courses/course[students &gt; 50]</div><div class="table-container"><table><tr><th>CODE</th><th>COURSE</th><th>STUDENTS</th></tr>
<xsl:for-each select="courses/course[students &gt; 50]"><tr><td class="code"><xsl:value-of select="code"/></td><td><xsl:value-of select="name"/></td><td><span class="student-badge"><xsl:value-of select="students"/></span></td></tr></xsl:for-each>
</table></div></div>
<div class="query-box"><div class="query-title"><span>02</span>Courses with 4 credits</div><div class="xpath-code">/courses/course[credits = 4]</div><div class="result-list"><xsl:for-each select="courses/course[credits = 4]"><div class="result-item"><span class="code"><xsl:value-of select="code"/></span><span><xsl:value-of select="name"/></span></div></xsl:for-each></div></div>
<div class="query-box"><div class="query-title"><span>03</span>Theory courses</div><div class="xpath-code">/courses/course[type = 'Theory']</div><div class="result-list"><xsl:for-each select="courses/course[type = 'Theory']"><div class="result-item"><span class="code"><xsl:value-of select="code"/></span><span><xsl:value-of select="name"/></span></div></xsl:for-each></div></div>
<div class="query-box"><div class="query-title"><span>04</span>Theory courses with high enrollment</div><div class="xpath-code">/courses/course[type = 'Theory' and students &gt; 50]</div><div class="result-list"><xsl:for-each select="courses/course[type = 'Theory' and students &gt; 50]"><div class="result-item"><span class="code"><xsl:value-of select="code"/></span><span><xsl:value-of select="name"/></span><span class="student-badge"><xsl:value-of select="students"/></span></div></xsl:for-each></div></div>
</section>
<section class="panel highlight"><div class="panel-header"><div><span class="section-tag">XSLT TRANSFORMATION</span><h2>High Enrollment Courses</h2></div><div class="rule">STUDENTS &gt; 40</div></div>
<p class="description">Courses are filtered using XPath and automatically sorted from highest to lowest enrollment using XSLT.</p>
<div class="enrollment-grid"><xsl:for-each select="courses/course[students &gt; 40]"><xsl:sort select="students" data-type="number" order="descending"/><div class="enrollment-card"><div class="rank">#<xsl:value-of select="position()"/></div><div class="enrollment-info"><span class="code"><xsl:value-of select="code"/></span><h3><xsl:value-of select="name"/></h3><p><xsl:value-of select="faculty"/></p></div><div class="enrollment-number"><strong><xsl:value-of select="students"/></strong><span>STUDENTS</span></div></div></xsl:for-each></div></section>
<footer><div>XML + XPath + XSLT</div><small>University Course Enrollment Analytics</small></footer>
</div></body></html>
</xsl:template></xsl:stylesheet>
