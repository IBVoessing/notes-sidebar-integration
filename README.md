# HCL Notes Sidebar Integrations

> [!IMPORTANT]  
> `Notes 14.5+` uses [WebView2-Runtime](https://developer.microsoft.com/de-de/microsoft-edge/webview2) for web content, greatly improving modern web compatibility.
>
> The xPage Sidebar now supports any modern web app (React, Angular, Vue.js) without ES5 transpilation or JavaScript polyfills.
 
## Overview

This repository contains two ways for creating sidebar integrations for the HCL Notes Client.

## Challange

HCL Notes `Version 14` utilizes an outdated browser engine (`Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:45.0) Gecko/20100101 /45.9.0` from approximately `2017`) to render web content. 

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

[View the Eclipse RCP Plugin Sidebar documentation](/eclipse-plugin-sidebar/README.md)

#### Advanced Integration Features

The Eclipse RCP Plugin approach enables powerful integration capabilities:

- **Access Notes Context**: Retrieve information about currently selected documents
- **Two-Way Communication**: 
  - Send data from Notes to your web application
  - Receive data from your web application back to the plugin
- **DOM Manipulation**: Directly interact with your web app's interface

[View the Advanced Integration Guide](/eclipse-plugin-sidebar/advanced-plugin/README.md)