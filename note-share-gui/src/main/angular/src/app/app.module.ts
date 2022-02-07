import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http";

import { NoteDetailsComponent } from './components/note-details/note-details.component';
import { NoteListComponent } from './components/note-list/note-list.component';
import { NoteShareComponent } from './components/note-share/note-share.component';
import { ReactiveFormsModule } from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    NoteDetailsComponent,
    NoteListComponent,
    NoteShareComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
