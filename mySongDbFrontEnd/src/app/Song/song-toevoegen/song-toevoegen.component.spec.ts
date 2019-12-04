import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SongToevoegenComponent } from './song-toevoegen.component';

describe('SongToevoegenComponent', () => {
  let component: SongToevoegenComponent;
  let fixture: ComponentFixture<SongToevoegenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SongToevoegenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SongToevoegenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
