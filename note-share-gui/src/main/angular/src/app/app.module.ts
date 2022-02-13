import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { DatePipe } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { ReactiveFormsModule } from "@angular/forms";

import { ToastrModule } from "ngx-toastr";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoteDetailsComponent } from './components/note-details/note-details.component';
import { NoteLinkComponent } from './components/note-link/note-link.component';
import { NoteListComponent } from './components/note-list/note-list.component';
import { NoteShareComponent } from './components/note-share/note-share.component';
import { NoteFormatPipe } from "./pipes/note-format.pipe";

@NgModule({
  declarations: [
    AppComponent,
    NoteDetailsComponent,
    NoteLinkComponent,
    NoteListComponent,
    NoteShareComponent,
    NoteFormatPipe
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    AppRoutingModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
