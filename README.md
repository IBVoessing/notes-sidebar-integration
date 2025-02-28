# HCL Notes Sidebar Integrations

## Overview

This repository contains two ways for creating sidebar integrations for the HCL Notes Client.

## Challange

HCL Notes utilizes an outdated browser engine (`Mozilla/5.0 (Windows NT 6.2; WOW64; rv:43.0) Gecko/20100101 /43.0.4` from approximately `2011`) to render web content. 

This presents significant limitations:

- Modern JavaScript frameworks are not supported
- Current CSS standards are unavailable
- Many contemporary browser APIs are inaccessible

These constraints make it challenging to integrate modern web applications directly into the Notes Client environment.

## Solution

We've developed two distinct methods to overcome these limitations:

### (1) xPage Sidebar 

This approach leverages an xPage as a host for a modern React application via an iFrame. The xPage is then configured to appear as a sidebar panel in the Notes Client.

[View the xPage Sidebar documentation](/xpage-sidebar/README.md)

### (2) Eclipse RCP Plugin Sidebar

This method uses an Eclipse RCP Plugin with a WebView component to host any modern web application. It offers enhanced functionality:

- Direct interaction with the Notes Client
- Access to Notes data and context
- Integration via an UpdateSite as a sidebar panel

[View the Eclipse RCP Plugin Sidebar documentation](/eclipse-rcp-plugin-sidebar/README.md)