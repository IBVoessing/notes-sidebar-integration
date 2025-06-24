<!-- omit in toc -->
# xPage Sidebar

> [!IMPORTANT]  
> `Notes 14.5+` uses [WebView2-Runtime](https://developer.microsoft.com/de-de/microsoft-edge/webview2) for web content, greatly improving modern web compatibility.
>
> The xPage Sidebar now supports any modern web app (React, Angular, Vue.js) without ES5 transpilation or JavaScript polyfills.
>
> For `Notes 14.5+` you can skip: 
>  - Step 2: Install the legacy plugin
>  - Step 3: Configure the legacy plugin
>  - Steps 4 & 5: For development, you can simply run `npx vite --host` which starts a development server with hot module replacement. No need to build the app each time you make changes. For production, you can still follow Step 5 to create an optimized build.

This approach leverages an xPage as a host for a modern React application via an iFrame. The xPage is then configured to appear as a sidebar panel in the Notes Client. To make this work we are using the [@vitejs/plugin-legacy](https://www.npmjs.com/package/@vitejs/plugin-legacy) plugin. This plugin allows us to transpile modern JavaScript code to ES5, which is compatible with the Notes Client's browser engine.

- [Prerequisites](#prerequisites)
- [Setup](#setup)
  - [Shortcut](#shortcut)
  - [1. Create a new React application](#1-create-a-new-react-application)
  - [2. Install the legacy plugin](#2-install-the-legacy-plugin)
  - [3. Configure the legacy plugin](#3-configure-the-legacy-plugin)
  - [4. Run the React app in development mode](#4-run-the-react-app-in-development-mode)
  - [5. Build the React app for production](#5-build-the-react-app-for-production)
  - [6. Create an xPage in HCL Domino Designer](#6-create-an-xpage-in-hcl-domino-designer)
  - [7. Display the xPage in the Notes Sidebar](#7-display-the-xpage-in-the-notes-sidebar)
- [Deployment: Add to Widget Catalog](#deployment-add-to-widget-catalog)


## Prerequisites

- HCL Notes and Designer client installation
- [Node.js](https://nodejs.org/en/download) installed [LTS version recommended]

## Setup

We are now creating an React application with Vite that can be integrated into the Notes Client. Vite is a build tool that aims to provide a faster and leaner development experience for modern web projects. It is a great fit for our use case because it supports the [@vitejs/plugin-legacy](https://www.npmjs.com/package/@vitejs/plugin-legacy) plugin.

### Shortcut

If you want to skip the setup process, you can clone this repository and use the provided React application.

Clone the repository and navigate to `.../xpage-sidebar/hcl-notes-sidebar`.

Then you can run the following commands:

```bash
npm install
npm run notes
```

This will build the app and give you an URL to access the application in your browser. You can use this URL inside an iFrame in an xPage to integrate the React app into the Notes Client.

### 1. Create a new React application

In this case we are using the `create-vite` command to create a new React application with TypeScript support.

```bash
npm create vite@latest hcl-notes-sidebar
```
You will be prompted to make two selections. Select `React` as framework and `TypeScript` as variant. You can also go with `JavaScript` if you prefer. 

Now you can navigate into the newly created directory. 

```bash
cd hcl-notes-sidebar
```

### 2. Install the legacy plugin

To make the React app work inside the Notes Client, we need to install the `@vitejs/plugin-legacy` plugin.

```bash
npm install @vitejs/plugin-legacy --save-dev
```

### 3. Configure the legacy plugin

To configure the legacy plugin, we need to add it to the `vite.config.ts` file.

```typescript
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import legacy from '@vitejs/plugin-legacy';

export default defineConfig({
  plugins: [
    react(),
    legacy({
      targets: ['dead'] // only dead seems to work
    })
  ]
});
```

You can make further adjustments to the configuration according to your needs. If you need more information, you can find the official documentation [here](https://vitejs.dev/config/). If you need to make further adjustments for the legacy plugin, you can find the official documentation [here](https://www.npmjs.com/package/@vitejs/plugin-legacy). Additional polyfills might be necessary to make your application work in the Notes Client.

### 4. Run the React app in development mode

To run the React application in development mode, you can use the following command:

```bash
npm run dev
```

This will start the development server and give you an URL to access the application in your browser.

> [!IMPORTANT]  
> If you want to display the React app in the Notes Client, you MUST build the application! Otherwise the legacy plugin will not be applied and the application will not work in the Notes Client.
>
> Therefore you can run `vite build && vite preview` to build the application and start a local server to preview the build. Additonally you can use the `--host` flag to expose the server to your local network.
> I recommend adding and additional script to your `package.json` file to make this process easier.
> ```json
> "scripts": {
>   "notes": "vite build && vite preview --host"
> }
>```
> This can then be executed with `npm run notes`.

### 5. Build the React app for production

To build the React application for production, you can use the following command:

```bash
npm run build
```

This will create a `dist` folder with the necessary files to host the application.

### 6. Create an xPage in HCL Domino Designer

To create an xPage in HCL Domino Designer, you can follow these steps:
 
1. Open HCL Domino Designer
2. Create a new XPage
3. Add an iFrame component to the xPage
4. Set the source of the iFrame to the URL of the React application (e.g. `http://localhost:4173`)

Sample xPage: 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xp:view xmlns:xp="http://www.ibm.com/xsp/core">
    <iframe
        style="position: fixed; height: 100%; width: 100%;"
        id="ibvsite"
        src="http://localhost:4173"
        frameBorder="0">
        Your browser does not support iframes.
    </iframe>
</xp:view>
```

### 7. Display the xPage in the Notes Sidebar

To display the xPage in the Notes Sidebar, you can follow these steps:

1. In the Notes Client, right click on the .nsf containing the previously created xPage
2. Application > Properties 
3. In the "Launch" tab (Rocket icon), select the xPage as the default launch option
4. `When opened in the Notes client` -> `Open designated xPage (Standard client)`
5. `XPage` -> `Select the xPage you created`

![Notes Client Launch Properties](/images/notes-client-launch-properties.png)

6. Open the .nsf in the Notes Client
7. The xPage should now be displayed in the Notes Client

![alt text](/images/notes-client-react-preview.png)

> [!IMPORTANT]
> Be sure that the React app has been build before you try to display it in the Notes Client. Otherwise the application will not work. 
>
> Either use `vite build` or `npm run build` to build and host the application on a server.
> Or use `vite build && vite preview` to build and start a local server to preview the build. See [step 4](#4-run-the-react-app-in-development-mode) for more information.

8. In the Notes Client, press Tools > Widgets > Add as Panel 
   
![alt text](/images/notes-client-xpage-install.png)

9. The xPage should now be displayed as a sidebar panel in the Notes Client

![alt text](/images/notes-client-sidebar-success.png)

> [!NOTE]
> This integration method has inherent constraints due to the outdated browser engine. Modern JavaScript and CSS features cannot be fully transpiled or polyfilled to work in the Notes Client environment. You may encounter compatibility issues particularly when:
> - Using complex component libraries or third-party dependencies
> - Implementing modern browser APIs that lack proper polyfills
> - Attempting to use authentication mechanisms like OAuth that require modern browser capabilities
> - Handling advanced CSS features or animations
>
> Consider these limitations when deciding if this approach meets your integration requirements or if the Eclipse RCP Plugin alternative might be more suitable for your use case.


## Deployment: Add to Widget Catalog

To add the xPage to the Widget Catalog, you can follow these steps:
1. Open the `My Widgets` tab in the sidebar panel
2. Right click on the xPage you have added as a panel in the previous step
3. Select `Publish to Catalog`
4. Fill out the necessary information and save the form
5. The xPage should now be available in the Widget Catalog

![alt text](/images/notes-widget-catalog.png)